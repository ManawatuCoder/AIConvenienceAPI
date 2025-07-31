module azurestoragemanagement {
    requires transitive io.clientcore.core;

    exports azurestoragemanagement;

    opens azurestoragemanagement to io.clientcore.core;
}
