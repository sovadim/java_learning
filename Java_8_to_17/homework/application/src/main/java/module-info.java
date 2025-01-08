module application {
    requires jmp.cloud.bank.impl;
    requires jmp.cloud.service.impl;
    requires jmp.yet.another.service.impl;
    requires jmp.dto;
    uses com.jmp.service.api.Service;
}