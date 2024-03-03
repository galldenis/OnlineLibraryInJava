


public class Newsletter {
    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    public void sendNewsletter() {
        System.out.println("Sending newsletter to: " + email);

    }
}
