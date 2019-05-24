import java.util.Arrays;

public class Message {
    private int MessageType;
    private int requestId;
    private String MethodReference;
    private int MethodId;
    private byte[] arguments;
    private OccurrenceBoletin boletin;

    public Message(int messageType, int requestId, String methodReference, int methodId, byte[] arguments, OccurrenceBoletin boletin) {
        MessageType = messageType;
        this.requestId = requestId;
        MethodReference = methodReference;
        MethodId = methodId;
        this.arguments = arguments;
        this.boletin = boletin;
    }

    public Message() {
    }

    public int getMessageType() {
        return MessageType;
    }

    public void setMessageType(int messageType) {
        MessageType = messageType;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getMethodReference() {
        return MethodReference;
    }

    public void setMethodReference(String methodReference) {
        MethodReference = methodReference;
    }

    public int getMethodId() {
        return MethodId;
    }

    public void setMethodId(int methodId) {
        MethodId = methodId;
    }

    public byte[] getArguments() {
        return arguments;
    }

    public void setArguments(byte[] arguments) {
        this.arguments = arguments;
    }

    public OccurrenceBoletin getBoletin() {
        return boletin;
    }

    public void setBoletin(OccurrenceBoletin boletin) {
        this.boletin = boletin;
    }

    @Override
    public String toString() {
        return "Message{" +
                "MessageType=" + MessageType +
                ", requestId=" + requestId +
                ", MethodReference='" + MethodReference + '\'' +
                ", MethodId=" + MethodId +
                ", arguments=" + Arrays.toString(arguments) +
                ", boletin=" + boletin +
                '}';
    }
}
