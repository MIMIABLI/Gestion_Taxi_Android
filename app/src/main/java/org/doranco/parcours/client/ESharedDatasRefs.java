package org.doranco.parcours.client;

public enum ESharedDatasRefs {

    USER_SHARED_DATAS("USER_SHARED_DATAS"),
    USER_SHARED_NAME("USER_SHARED_NAME"),
    USER_SHARED_FIRSTNAME("USER_SHARED_FIRSTNAME"),
    USER_SHARED_MAIL("USER_SHARED_MAIL"),
    USER_SHARED_TEL("USER_SHARED_TEL"),
    USER_SHARED_LOGIN("USER_SHARED_LOGIN"),
    USER_SHARED_TOKEN("USER_SHARED_TOKEN"),
    USER_SHARED_PASSWORD("USER_SHARED_PASSWORD"),
    USER_SHARED_ID("USER_SHARED_ID");

    private String userSharedRef;

    ESharedDatasRefs(String userSharedRef) {
        this.userSharedRef = userSharedRef;
    }
}
