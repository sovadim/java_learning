module jmp.yet.another.service.impl {
    exports com.jmp.yet.another.service.impl;
    requires transitive jmp.service.api;
    requires jmp.dto;
    uses com.jmp.service.api.Service;
}