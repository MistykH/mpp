package Domain;

public class Client extends Entity<Integer> {
    private String fullName;

    public Client(int clientId, String fullName) {
        super(clientId);
        this.fullName = fullName;
    }



    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Override
    public String toString() {
        return "Client{" +
                "clientId=" + this.getId() +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
