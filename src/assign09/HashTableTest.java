package assign09;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    Random rng = new Random();
    private  HashTable<Integer,Integer> intTableSmall;
    private HashTable<String,String> stringTableSmall;
    private HashTable<Integer,Integer> intTableLarge;
    private HashTable<String,String> stringTableLarge;

    @BeforeEach
    void setUp() {
        intTableSmall = new HashTable<>();
        stringTableSmall = new HashTable<>();
        intTableLarge = new HashTable<>();
        stringTableLarge = new HashTable<>();
        //integer table initialization---------
        for(int l = 0; l < 30; l++) {
                intTableSmall.put(l, rng.nextInt(0,l + 100000));
        }
        for(int l = 0; l < 3000; l++) {
                intTableLarge.put(l, rng.nextInt(0,l + 100000));
        }
        //string table initialization-------------
        stringTableSmall.put("Abdul", "hello");
        stringTableSmall.put("Bull", "I");
        stringTableSmall.put("Corn", "am");
        stringTableSmall.put("Eezap", "here");
        stringTableSmall.put("Fire", "to get turnt");
        for(int l = 0; l < 3000; l++) {
            stringTableLarge.put(l + "", rng.nextInt(0,l + 100000) + "");
        }
    }

    @Test
    void clear() {
        intTableSmall.clear();
        assertTrue(intTableSmall.isEmpty());
        intTableSmall.put(4,1);
        assertEquals(1,intTableSmall.get(4));
    }

    @Test
    void containsKey() {
        intTableSmall.put(3, 4);
        assertFalse(intTableSmall.containsKey(40));
        assertTrue(intTableSmall.containsKey(3));
        intTableLarge.put(4001, 69);
        assertTrue(intTableLarge.containsKey(4001));
        assertFalse(intTableLarge.containsKey(3999));
    }

    @Test
    void containsValue() {
        intTableSmall.put(31, 6);
        assertTrue(intTableSmall.containsValue(6));
        intTableLarge.put(3001, 19999999);
        assertTrue(intTableLarge.containsValue(19999999));
    }

    @Test
    void entries() {
        HashTable<Integer,Integer> temp = new HashTable<>();
        for(int i = 0;i<1000;i++) {
            temp.put(i,i);
        }
        List<MapEntry<Integer, Integer>> hashList = temp.entries();
        List<MapEntry<Integer, Integer>> mapList = new ArrayList<>();
        for(int i = 0;i<1000;i++) {
            mapList.add(new MapEntry<>(i,i));
        }
        for(MapEntry<Integer,Integer> map:mapList ) {
            assertTrue(hashList.contains(map));
        }
    }

    @Test
    void get() {
        intTableSmall.put(31, 7);
        assertEquals(intTableSmall.get(31), 7);
        intTableLarge.put(19000, 200);
        assertEquals(intTableLarge.get(19000), 200);
    }

    @Test
    void isEmpty() {
        HashTable<Integer,Integer> temp = new HashTable<>();
        assertTrue(temp.isEmpty());
        intTableSmall.clear();
        assertTrue(intTableSmall.isEmpty());
        assertFalse(intTableLarge.isEmpty());
    }

    @Test
    void put() {
        intTableLarge.put(10000,17544);
        assertEquals(17544,intTableLarge.get(10000));
        assertTrue(intTableLarge.containsKey(10000));
        assertTrue(intTableLarge.containsValue(17544));
    }

    @Test
    void putOverwrite() {
        assertEquals(3000, intTableLarge.size());
        intTableLarge.put(10000,1);
        assertEquals(1,intTableLarge.get(10000));
        assertEquals(3001, intTableLarge.size());
        intTableLarge.put(10000,5);
        assertEquals(5,intTableLarge.get(10000));
        assertEquals(3001, intTableLarge.size());
    }

    @Test
    void remove() {
        HashTable<Integer,Integer> temp = new HashTable<>();
        assertNull(temp.remove(1));
        intTableLarge.remove(1);
        assertFalse(intTableLarge.containsKey(1));
        intTableLarge.put(10000,5);
        assertEquals(5,intTableLarge.get(10000));
        intTableLarge.remove(10000);
        assertFalse(intTableLarge.containsKey(10000));
    }

    @Test
    void size() {
        assertEquals(intTableSmall.size(), 30);
        assertEquals(intTableLarge.size(), 3000);
        for (int i = 1; i < 31; i++){
            intTableSmall.put(30 + i, i);
            intTableLarge.put(3000 + i, i);
        }
        assertEquals(intTableSmall.size(), 60);
        assertEquals(intTableLarge.size(), 3030);
    }

    //String HashTable tests-----------------------------------

    @Test
    void stringGet() {
        assertEquals(stringTableSmall.get("Bull"), "I");
        assertEquals(stringTableSmall.get("Corn"), "am");
        stringTableLarge.put("1500", "49");
        assertEquals(stringTableLarge.get("1500"), "49");
        stringTableLarge.remove("1500");
        assertEquals(stringTableLarge.get("1500"), null);
    }

    @Test
    void stringRemove() {
        stringTableSmall.put("Abdul", "9");
        assertEquals(stringTableSmall.remove("Abdul"), "9");
        assertEquals(stringTableSmall.remove("Abdul"), null);
        stringTableLarge.put("1500", "49");
        assertEquals(stringTableLarge.get("1500"), "49");
        stringTableLarge.remove("1500");
        assertEquals(stringTableLarge.get("1500"), null);
    }

    @Test
    void stringContainsKey() {
        assertFalse(stringTableSmall.containsKey("LazyBones"));
        assertFalse(stringTableLarge.containsKey("NotANumber"));
        assertTrue(stringTableSmall.containsKey("Fire"));
        assertTrue(stringTableLarge.containsKey("2999"));
    }

    @Test
    void stringContainsValue() {
        assertFalse(stringTableSmall.containsValue("90"));
        assertFalse(stringTableLarge.containsValue("SimplyDoesNotExist"));
        assertTrue(stringTableSmall.containsValue("to get turnt"));
        stringTableLarge.put("300", "1000001");
        assertTrue(stringTableLarge.containsValue("1000001"));
    }

    //Empty HashTable tests------------------------------
    @Test
    void emptyTests() {
        HashTable<Integer,Integer> temp = new HashTable<>();
        assertNull(temp.remove(1));
        assertNull(temp.get(1));
        assertFalse(temp.containsKey(1));
        assertFalse(temp.containsValue(1));
        assertEquals(0,temp.size());

    }


}