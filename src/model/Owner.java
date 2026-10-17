package model;

public class Owner {

    private int ownerId;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String petName;

    public Owner() {
    }

    public Owner(int ownerId, String name, String phone,
                 String email, String address, String petName) {

        this.ownerId = ownerId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.petName = petName;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }
}