package org.springboot.actuator.config.actuator;

import org.springframework.boot.actuate.endpoint.annotation.DeleteOperation;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "note")
public class NoteEndpoint {

    private Map<String, Object> noteContent = new HashMap<>();

    @ReadOperation
    public Map<String, Object> getNote() {
        return noteContent;
    }

    @WriteOperation
    public Map<String, Object> writeNote(String key, Object value) {
        noteContent.put(key, value);

        return noteContent;
    }

    @DeleteOperation
    public Map<String, Object> deleteNote(String key) {
        noteContent.remove(key);

        return noteContent;
    }
}

//      포스트맨으로 확인 순서
//
//      [GET] http://localhost:8080/custom-path/note
//      {}
//
//      [POST] http://localhost:8080/custom-path/note
//      {
//          "key" : "description",
//          "value" : "설명 부분"
//      }
//
//      [GET] http://localhost:8080/custom-path/note
//      {
//          "description": "설명 부분"
//      }
//
//      [DELETE] http://localhost:8080/custom-path/note?key=description
//      {}
//
//      [GET] http://localhost:8080/custom-path/note
//      {}