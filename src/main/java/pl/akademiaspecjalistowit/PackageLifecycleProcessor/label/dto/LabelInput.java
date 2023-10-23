package pl.akademiaspecjalistowit.PackageLifecycleProcessor.label.dto;

import lombok.Getter;

@Getter
public class LabelInput {
    private String packageSize;
    private UserDto receiver;
    private UserDto sender;

    public LabelInput(String packageSize,
                      UserDto receiver,
                      UserDto sender) {
        this.packageSize = packageSize;
        this.receiver = receiver;
        this.sender = sender;
    }

}
