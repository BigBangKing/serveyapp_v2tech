package com.example.serveyapp_rifatmahmud_v2tech.data.pojo;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
public class schema2x {
    @SerializedName("question")
    @Expose
    private String question;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("options")
    @Expose
    private String options;
    @SerializedName("required")
    @Expose
    private Boolean required;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public Boolean getRequired() {
        return required;
    }

    public void setRequired(Boolean required) {
        this.required = required;
    }

}