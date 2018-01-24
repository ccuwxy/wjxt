package cn.wenjianxitong.www.permanentfile.controller;

import cn.wenjianxitong.www.FileInfo;
import cn.wenjianxitong.www.utils.MongoDbPool;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


public class Upload {
    private String sFileToken;
//    public static MongoClient mongoClient;
//    public static MongoDatabase mongoDatabase;
    public Upload(FileInfo fileInfo) throws ParseException {
        MongoDatabase mongoDatabase = MongoDbPool.getConn("wjxt");
        MongoCollection<Document> collection = mongoDatabase.getCollection("permanentfile");

        long currentTime=System.currentTimeMillis()/1000;
        Document document = new Document("file_token", fileInfo.getMd5FileName()).
                append("orgin_file_name", fileInfo.getOrginFileName()).
                append("size", fileInfo.getFile_size()).
                append("file_byte", fileInfo.getFile_byte()).
                append("create_time", fileInfo.getCreateTime());

        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        collection.insertMany(documents);
        this.sFileToken=fileInfo.getMd5FileName();

    }

    public String getsFileToken() {
        return sFileToken;
    }
}
