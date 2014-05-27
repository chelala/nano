package com.leansoft.nano.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.leansoft.nano.Format;
import com.leansoft.nano.IReader;
import com.leansoft.nano.annotation.SeeAlso;
import com.leansoft.nano.annotation.Type;
import com.leansoft.nano.annotation.schema.AnyElementSchema;
import com.leansoft.nano.annotation.schema.AttributeSchema;
import com.leansoft.nano.annotation.schema.ElementSchema;
import com.leansoft.nano.annotation.schema.RootElementSchema;
import com.leansoft.nano.annotation.schema.ValueSchema;
import com.leansoft.nano.exception.MappingException;
import com.leansoft.nano.exception.ReaderException;
import com.leansoft.nano.transform.Transformer;
import com.leansoft.nano.util.StringUtil;
import com.leansoft.nano.util.TypeReflector;

import dalvik.system.DexFile;
import dalvik.system.PathClassLoader;

/**
 * IReader implementation using DOM xml parser.
 * 
 * @author bulldog
 * 
 */
public class XmlDOMReader implements IReader {

    protected Format format;

    protected static final ThreadLocal<DocumentBuilder> builderLocal = new ThreadLocal<DocumentBuilder>() {
        @Override
        protected DocumentBuilder initialValue() {
            try {

                DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
                dbf.setNamespaceAware(true);

                return dbf.newDocumentBuilder();
            } catch (ParserConfigurationException e) {
                throw new RuntimeException("Failed to create DocumentBuilder!", e);
            }
        }
    };

    public XmlDOMReader() {
        this(new Format());
    }

    public XmlDOMReader(Format format) {
        this.format = format;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T read(Class<? extends T> type, InputStream source) throws ReaderException, MappingException {

        this.validate(type, source);

        try {
            DocumentBuilder db = builderLocal.get();
            Document doc = db.parse(source);

            Element rootElement = doc.getDocumentElement();
            MappingSchema ms = MappingSchema.fromClass(type);
            RootElementSchema res = ms.getRootElementSchema();

            String xmlName = res.getXmlName();

            // simple validation only for root element
            if (!xmlName.equalsIgnoreCase(rootElement.getLocalName())) {
                throw new ReaderException("Root element name mismatch, " + rootElement.getLocalName() + " != " + xmlName);
            }

            Object obj = this.buildObjectFromType(type);

            this.read(obj, rootElement);

            return (T) obj;
        } catch (MappingException me) {
            throw me;
        } catch (Exception e) {
            throw new ReaderException("Error to read/descrialize object", e);
        }
    }

    @Override
    public <T> T read(Class<? extends T> type, String source) throws ReaderException, MappingException {
        try {
            return this.read(type, new ByteArrayInputStream(source.getBytes(format.getEncoding())));
        } catch (UnsupportedEncodingException e) {
            throw new ReaderException("Encoding is not supported", e);
        }
    }

    @Override
    public <T> T read(Class<? extends T> type, Reader source) throws ReaderException, MappingException {
        if (source == null) {
            throw new ReaderException("Reader is null!");
        }

        try {
            return this.read(type, StringUtil.reader2String(source));
        } catch (IOException e) {
            throw new ReaderException("IO error when converting reader to string!", e);
        }
    }

    protected <T> void validate(Class<? extends T> type, InputStream source) throws ReaderException {
        if (type == null) {
            throw new ReaderException("Can not read, type is null!");
        }

        if (source == null) {
            throw new ReaderException("InputStream is null!");
        }

        if (Transformer.isPrimitive(type)) {
            throw new ReaderException("Can not read primitive or enum type object, " + "only Nano bindable complex type object can be accepted!");
        }
    }

    protected void read(Object obj, Element element) throws Exception {
        this.readAttribute(obj, element);

        boolean hasText = this.readText(obj, element);
        if (hasText) {
            return; // no further read if xml text presents
        }

        List<Element> anyElements = new ArrayList<Element>();

        this.readElement(obj, element, anyElements);

        this.readAnyElement(obj, anyElements);
    }

    private void readAttribute(Object obj, Element element) throws Exception {
        MappingSchema ms = MappingSchema.fromObject(obj);

        // read xml attributes
        Map<String, AttributeSchema> asm = ms.getXml2AttributeSchemaMapping();
        if (!asm.isEmpty() && element.hasAttributes()) {
            for (String attrXmlName : asm.keySet()) {
                AttributeSchema as = asm.get(attrXmlName);
                String attrValue = element.getAttributeNS(null, attrXmlName);
                if (!StringUtil.isEmpty(attrValue)) {
                    Field field = as.getField();
                    Object filedValue = Transformer.read(attrValue, field.getType());
                    if (filedValue != null) {
                        field.set(obj, filedValue);
                    }
                }
            }
        }
    }

    private boolean readText(Object obj, Element element) throws Exception {
        MappingSchema ms = MappingSchema.fromObject(obj);

        // read xml value if any
        ValueSchema vs = ms.getValueSchema();
        if (vs != null) {
            Field field = vs.getField();
            String text = element.getTextContent();
            if (!StringUtil.isEmpty(text)) {
                Object fieldValue = Transformer.read(text, field.getType());
                if (fieldValue != null) {
                    field.set(obj, fieldValue);
                }
            }

            return true;
        } else {
            return false;
        }
    }

    private void readElement(Object obj, Element element, List<Element> anyChildElements) throws Exception {
        MappingSchema ms = MappingSchema.fromObject(obj);

        // read xml element
        Map<String, Object> xml2SchemaMapping = ms.getXml2SchemaMapping();
        NodeList nodeList = element.getChildNodes();
        if (nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element childElement = (Element) node;
                    String localName = node.getLocalName();

                    Object schemaObj = xml2SchemaMapping.get(localName);
                    // Check if attr type present

                    if (schemaObj != null && schemaObj instanceof ElementSchema) { // found
                                                                                   // match
                                                                                   // element
                        ElementSchema es = (ElementSchema) schemaObj;
                        Field field = es.getField();
                        Class<?> fieldType = field.getType();

                        Class<?> instanceElementClass = processInstanceType(node, fieldType);

                        if (es.isList()) { // collection
                            @SuppressWarnings("unchecked")
                            List<Object> list = (List<Object>) field.get(obj);
                            if (list == null) {
                                list = new ArrayList<Object>();
                                field.set(obj, list);
                            }

                            Class<?> parameterizedType = es.getParameterizedType();

                            // primitive
                            if (Transformer.isPrimitive(parameterizedType)) {
                                String xmlValue = childElement.getTextContent();
                                if (!StringUtil.isEmpty(xmlValue)) {
                                    Object fieldValue = Transformer.read(xmlValue, parameterizedType);
                                    if (fieldValue != null) {
                                        list.add(fieldValue);
                                    }
                                }
                            } else {
                                Object newObj = this.buildObjectFromType(parameterizedType);
                                this.read(newObj, childElement);
                                list.add(newObj);
                            }

                        } else { // single field value
                            // primitive
                            if (Transformer.isPrimitive(fieldType)) {
                                String xmlValue = childElement.getTextContent();
                                if (!StringUtil.isEmpty(xmlValue)) {
                                    Object fieldValue = Transformer.read(xmlValue, fieldType);
                                    if (fieldValue != null) {
                                        field.set(obj, fieldValue);
                                    }
                                }
                            } else {
                                if (null != instanceElementClass) {
                                    Object newObj = this.buildObjectFromType(instanceElementClass);
                                    this.read(newObj, childElement);
                                    field.set(obj, newObj);
                                } else {
                                    Object newObj = this.buildObjectFromType(fieldType);
                                    this.read(newObj, childElement);
                                    field.set(obj, newObj);
                                }
                            }
                        }
                    } else if (anyChildElements != null) {
                        anyChildElements.add(childElement);
                    }

                }
            }
        }
    }

    private Class<?> processInstanceType(Node node, Class<?> fieldType) {
        final Attr xsiTypeAttr = (Attr) node.getAttributes().getNamedItemNS("http://www.w3.org/2001/XMLSchema-instance", "type");
        if (null != xsiTypeAttr) {

            // Get all types with annotation "Type"
            final SeeAlso seeAlsoAnnotation = fieldType.getAnnotation(SeeAlso.class);
            if (null != seeAlsoAnnotation) {
                for (Class<?> class2See : seeAlsoAnnotation.value()) {
                    final Type typeAnnotation = class2See.getAnnotation(Type.class);
                    if (null != typeAnnotation) {
                        final String nodeValue = xsiTypeAttr.getNodeValue();
                        final String[] splittedNodeValue = nodeValue.split(":");
                        if (2 == splittedNodeValue.length) {
                            if (splittedNodeValue[1].equals(typeAnnotation.name())) {
                                // TODO: check namespace in [0] is the one of
                                // the PackageInfo annotated class of the same
                                // package of the class to see
                                return class2See;
                            } 
                        } else if (nodeValue.equals(typeAnnotation.name())) {
                            return class2See;
                        }

                    }
                }
            }
        }
        return null;
    }

    protected void readAnyElement(Object obj, List<Element> anyElements) throws Exception {
        MappingSchema ms = MappingSchema.fromObject(obj);

        AnyElementSchema aes = ms.getAnyElementSchema();
        if (aes != null && anyElements != null && anyElements.size() > 0) {
            Field field = aes.getField();
            field.set(obj, anyElements);
        }
    }

    protected Object buildObjectFromType(Class<?> type) throws Exception {
        try {
            Constructor<?> con = TypeReflector.getConstructor(type);
            Object obj = con.newInstance();
            return obj;
        } catch (NoSuchMethodException nsme) {
            throw new ReaderException("No-arg contructor is missing, type = " + type.getName());
        }
    }

}
