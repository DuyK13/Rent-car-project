package com.iuh.rencar_project.utils.enums;

/**
 * @author Duy Trần Thế
 * @version 1.0
 * @date 5/29/2021 12:36 PM
 */
public enum BillType {
    SA_HINH("Sa hình"), DUONG_TRUONG("Đường trường"), LIEN_TINH("Liên tỉnh");

    private final String name;

    BillType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
