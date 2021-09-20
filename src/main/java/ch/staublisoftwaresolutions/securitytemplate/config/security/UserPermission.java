package ch.staublisoftwaresolutions.securitytemplate.config.security;

public enum  UserPermission {

    WRITE("write"),
    READ("read");

    final private String name;

    UserPermission(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
