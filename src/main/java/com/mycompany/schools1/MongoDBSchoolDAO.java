/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.schools1;

/**
 *
 * @author Babak
 */

import java.util.ArrayList;
import java.util.List;
 import org.bson.types.ObjectId;

import com.mycompany.schools1.SchooltoD;
import com.mycompany.schools1.School;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;




public class MongoDBSchoolDAO {
    private DBCollection col;
    public MongoDBSchoolDAO(MongoClient mongo) {
        this.col = mongo.getDB("test").getCollection("Schools");
    }
    
    public School createSchool(School s) {
        DBObject doc = SchooltoD.toDBObject(s);
        this.col.insert(doc);
        ObjectId id = (ObjectId) doc.get("_id");
        s.setId(id.toString());
        return s;
    }
     public void updateSchool(School p) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(p.getId())).get();
        this.col.update(query, SchooltoD.toDBObject(p));
    }
 
    public List<School> readAllSchool() {
        List<School> data = new ArrayList<School>();
        DBCursor cursor = col.find();
        while (cursor.hasNext()) {
            DBObject doc = cursor.next();
            School s = SchooltoD.toSchool(doc);
            data.add(s);
        }
        return data;
    }
     public void deleteSchool(School s) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(s.getId())).get();
        this.col.remove(query);
    }
 
    public School readSchool(School s) {
        DBObject query = BasicDBObjectBuilder.start()
                .append("_id", new ObjectId(s.getId())).get();
        DBObject data = this.col.findOne(query);
        return SchooltoD.toSchool(data);
    }
    
    
    
}
