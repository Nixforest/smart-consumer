/**
 * CommentMobileBLO.java
 * 9 Jan 2013
 * SmartConsumer.
 */
package com.gae.java.smartconsumer.mobile.blo;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.gae.java.smartconsumer.blo.CommentBLO;
import com.gae.java.smartconsumer.model.Comment;

/**
 * Mobile class for Comment object.
 * @version v1.0 08/01/2013
 * @author NguyenPT
 */
public class CommentMobileBLO {
    /**
     * Constructor.
     */
    protected CommentMobileBLO() {
        // Do nothing
    }
    /**
     * Get comment by Id.
     * @param id Id of comment
     * @return JSONObject Object JSON
     * @throws JSONException Exception
     */
    public static JSONObject getCommentById(long id) throws JSONException {
        Comment comment = CommentBLO.INSTANCE.getCommentById(id);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", comment.getId());
        jsonObject.put("dealId", comment.getDealId());
        jsonObject.put("username", comment.getUsername());
        jsonObject.put("content", comment.getContent());
        jsonObject.put("commitTime", comment.getCommitTime());
        return jsonObject;
    }
    /**
     * Get list comments by deal id.
     * @param dealId Id of deals
     * @return List comments
     * @throws JSONException Exception
     */
    public static List<JSONObject> getCommentsByDealId(long dealId) throws JSONException {
        List<JSONObject> listJson = new ArrayList<JSONObject>();
        List<Comment> comments = CommentBLO.INSTANCE.getCommentsByDealId(dealId);
        if (!comments.isEmpty()) {
            for (Comment comment : comments) {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", comment.getId());
                jsonObject.put("dealId", comment.getDealId());
                jsonObject.put("username", comment.getUsername());
                jsonObject.put("content", comment.getContent());
                jsonObject.put("commitTime", comment.getCommitTime());
                listJson.add(jsonObject);
            }
        }
        return listJson;
    }
}
