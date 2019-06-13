import org.json.JSONException;
import org.json.JSONObject;

public class Message {
    private int MessageType; // 0-request | 1-reply
    private int requestId;
    private String MethodReference;
    private int MethodId;
    private String arguments;
    private OccurrenceBoletin boletin;

    public Message(int messageType, int requestId, String methodReference, int methodId, String arguments, OccurrenceBoletin boletin) {
        this.MessageType = messageType;
        this.requestId = requestId;
        this.MethodReference = methodReference;
        this.MethodId = methodId;
        this.arguments = arguments;
        this.boletin = boletin;
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

    public String getArguments() {
        return arguments;
    }

    public void setArguments(String arguments) {
        this.arguments = arguments;
    }

    public OccurrenceBoletin getBoletin() {
        return boletin;
    }

    public void setBoletin(OccurrenceBoletin boletin) {
        this.boletin = boletin;
    }

    public JSONObject getJSONMessage(Message msg){
        JSONObject object_to_return = new JSONObject();
        try {
            object_to_return.put("MessageType",msg.getMessageType());
            object_to_return.put("requestId",msg.getRequestId());
            object_to_return.put("MethodReference",msg.getMethodReference());
            object_to_return.put("MethodId",msg.getMethodId());
            object_to_return.put("arguments",msg.getArguments());
            object_to_return.put("boletin",msg.getBoletin().getJSONOccurenceBoletin(msg.getBoletin()));
        }catch (Exception e){

        }
        return object_to_return;
    }

    public Message convertStrJSONToMessage(String JSONSTRMessage){
        Message msg = null;
        OccurrenceBoletin occurrenceBoletin = new OccurrenceBoletin(null,null,"","",false,"","",false);
        try {
            JSONObject obj = new JSONObject(JSONSTRMessage);
            occurrenceBoletin=occurrenceBoletin.convertStrJSONToOccurrenceBoletin(obj.get("boletin").toString());
            msg = new Message(obj.getInt("MessageType"),obj.getInt("requestId"),obj.getString("MethodReference"),obj.getInt("MethodId"),obj.getString("arguments"),occurrenceBoletin);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return msg;
    }

    @Override
    public String toString() {
        return "Message{" +
                "MessageType=" + MessageType +
                ", requestId=" + requestId +
                ", MethodReference='" + MethodReference + '\'' +
                ", MethodId=" + MethodId +
                ", arguments=" + arguments +
                ", boletin=" + boletin +
                '}';
    }
}
