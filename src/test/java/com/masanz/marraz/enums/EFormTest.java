package com.masanz.marraz.enums;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EFormTest {

    @Test
    void toText() {
        EForm eForm = EForm.valueOf("LINE");
        assertEquals("LINE", eForm.toString());
    }

    @Test
    void valueOf() {
        EForm eForm = EForm.valueOf("LINE");
        assertEquals(EForm.LINE, eForm);
    }
}