package com.shortner.url.utility;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Base62Test {

    @Test
    void encode_and_decode_areInverses() {
        long original = 99L;
        String encoded = Base62.encode(original);
        long decoded = Base62.decode(encoded);
        assertEquals(original, decoded);
    }

    @Test
    void encode_zero_returnsFirstAlphabetChar() {
        assertEquals("0", Base62.encode(0L));
    }

    @Test
    void encode_isDeterministic() {
        assertEquals(Base62.encode(12345L), Base62.encode(12345L));
    }
}