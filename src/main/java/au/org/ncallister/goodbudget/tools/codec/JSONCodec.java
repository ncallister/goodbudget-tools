/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.org.ncallister.goodbudget.tools.codec;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ncallister
 */
public abstract class JSONCodec<Type> extends Codec<Type, JSONObject>
{
  @Override
  protected JSONObject instantiateRemote()
  {
    return new JSONObject();
  }
  
  public List<Type> decodeArray(JSONArray array) throws CodecException
  {
    ArrayList<Type> result = new ArrayList<>();
    for (int i = 0 ; i < array.length() ; ++i)
    {
      result.add(decode(array.getJSONObject(i)));
    }
    return result;
  }
}
