/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.schools1;

import org.bson.types.ObjectId;
/**
 *
 * @author Babak
 */

import com.mycompany.schools1.School;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;

public class SchooltoD {

    public static DBObject toDBObject(School s) {
        BasicDBObjectBuilder builder = BasicDBObjectBuilder.start()
                .append("name", s.getName()).append("code", s.getCode()).append("address", s.getAddress()).append("emailDomain", s.getEmailDomain());
        if (s.getId() != null) {
            builder = builder.append("_id", new ObjectId(s.getId()));
        }

        return builder.get();

    }

    public static School toSchool(DBObject doc) {
        School s = new School();
        s.setName((String) doc.get("name"));
        s.setCode((String) doc.get("code"));
        s.setAddress((String) doc.get("address"));
        s.setEmailDomain((String) doc.get("emailDomain"));
        ObjectId id = (ObjectId) doc.get("_id");
        s.setId(id.toString());
        return s;
    }

}
