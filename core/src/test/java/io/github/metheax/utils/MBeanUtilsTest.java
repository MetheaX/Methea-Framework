package io.github.metheax.utils;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MBeanUtilsTest {

    // Simple test bean
    static class SampleBean {
        private String name;
        private String email;
        private Integer age;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }
        public Integer getAge() { return age; }
        public void setAge(Integer age) { this.age = age; }
    }

    @Test
    void getNullProperties_allNullFields_returnsAllNullPropertyNames() {
        SampleBean bean = new SampleBean();
        String[] nullProps = MBeanUtils.getNullProperties(bean);
        List<String> result = Arrays.asList(nullProps);
        assertTrue(result.contains("name"));
        assertTrue(result.contains("email"));
        assertTrue(result.contains("age"));
    }

    @Test
    void getNullProperties_noNullFields_returnsOnlyClassProperty() {
        SampleBean bean = new SampleBean();
        bean.setName("John");
        bean.setEmail("john@example.com");
        bean.setAge(30);

        String[] nullProps = MBeanUtils.getNullProperties(bean);
        List<String> result = Arrays.asList(nullProps);
        // Only "class" property (from Object.getClass()) should be in the null-excluded list
        // (BeanWrapper always includes getClass() as a property, which returns non-null)
        assertFalse(result.contains("name"));
        assertFalse(result.contains("email"));
        assertFalse(result.contains("age"));
    }

    @Test
    void getNullProperties_someNullFields_returnsNullFieldNames() {
        SampleBean bean = new SampleBean();
        bean.setName("John");
        // email and age remain null

        String[] nullProps = MBeanUtils.getNullProperties(bean);
        List<String> result = Arrays.asList(nullProps);
        assertFalse(result.contains("name"));
        assertTrue(result.contains("email"));
        assertTrue(result.contains("age"));
    }

    @Test
    void getNullProperties_idFieldAlwaysIncluded() {
        // MBeanUtils always includes "id" even if it's non-null
        BeanWithId bean = new BeanWithId();
        bean.setId("some-id");
        bean.setName("test");

        String[] nullProps = MBeanUtils.getNullProperties(bean);
        List<String> result = Arrays.asList(nullProps);
        assertTrue(result.contains("id"), "id field should always be included per MetheaConstant.ID check");
    }

    static class BeanWithId {
        private String id;
        private String name;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}
