package com.bookdb.book.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;

@SuppressWarnings("CallToPrintStackTrace")
@Entity
@Table(name = "json_book", schema = "books")
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class JsonBook {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    @JsonIgnore
    private String book;

    @Column(name = "image_url")
    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("name")
    public String getName() {
        return getJsonFieldAsString();
    }

    @JsonProperty("author")
    public String getAuthor() {
        return getJsonFieldAsStringAuthor();
    }

    @JsonProperty("lastReaded")
    public BigDecimal getlastReaded() {
        return getJsonFieldAsBigDecimal();
    }

    public void setName(String name) {
        setJsonField("name", name);
    }

    public void setAuthor(String author) {
        setJsonField("author", author);
    }

    public void setlastReaded(BigDecimal lastReaded) {
        setJsonField("lastReaded", lastReaded);
    }

    private String getJsonFieldAsString() {
        try {
            JsonNode node = objectMapper.readTree(book);
            return node.has("name") ? node.get("name").asText() : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getJsonFieldAsStringAuthor() {
        try {
            JsonNode node = objectMapper.readTree(book);
            return node.has("author") ? node.get("author").asText() : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private BigDecimal getJsonFieldAsBigDecimal() {
        try {
            JsonNode node = objectMapper.readTree(book);
            return node.has("lastReaded") ? node.get("lastReaded").decimalValue() : null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setJsonField(String fieldName, Object value) {
        try {
            ObjectNode node = book == null ? objectMapper.createObjectNode() : (ObjectNode) objectMapper.readTree(book);
            if (value == null) {
                node.remove(fieldName);
            } else {
                node.putPOJO(fieldName, value);
            }
            book = node.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
