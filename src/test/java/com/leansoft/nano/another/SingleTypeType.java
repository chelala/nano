package com.leansoft.nano.another;

public enum SingleTypeType {

    SCENT("Scent"),

    DNA("DNA"),

    EAR("Ear "),

    FACE("Face"),

    FINGER("Finger"),

    FOOT("Foot"),

    VEIN("Vein"),

    HAND_GEOMETRY("HandGeometry"),

    IRIS("Iris"),

    RETINA("Retina"),

    VOICE("Voice"),

    GAIT("Gait"),

    KEYSTROKE("Keystroke"),

    LIP_MOVEMENT("LipMovement"),

    SIGNATURE_SIGN("SignatureSign"),

    LATENT_PALM_PRINT("LatentPalmPrint"),

    PALM("Palm"),

    LATENT_FINGER_PRINT("LatentFingerPrint"),

    LATENT_FACE("LatentFace"),

    MARK("Mark"),

    TATOO("Tatoo"),

    LATENT_DNA("LatentDNA"),

    LATENT_VOICE("LatentVoice"),

    SIMULTANEOUS_DERMATOSCOPY("Simultaneous_Dermatoscopy"),

    SIMULTANEOUS_FACE("Simultaneous_Face");

    private final String value;

    SingleTypeType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static SingleTypeType fromValue(String v) {
        if (v != null) {
            for (SingleTypeType c : SingleTypeType.values()) {
                if (c.value.equals(v)) {
                    return c;
                }
            }
        }
        throw new IllegalArgumentException(v);
    }
}

