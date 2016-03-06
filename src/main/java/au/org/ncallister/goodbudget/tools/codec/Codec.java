/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.org.ncallister.goodbudget.tools.codec;

import java.util.Map;

/**
 *
 * @author ncallister
 */
public abstract class Codec<LocalType, RemoteType>
{
  private Map<String, FieldTranscriber<RemoteType, LocalType>> fieldDecoders;
  private Map<String, FieldTranscriber<LocalType, RemoteType>> fieldEncoders;
  
  protected abstract LocalType instantiateLocal();
  protected abstract RemoteType instantiateRemote();
  
  public void decode(RemoteType source, LocalType sink) throws CodecException
  {
    for (FieldTranscriber<RemoteType, LocalType> decoder : 
            fieldDecoders.values())
    {
      decoder.transcribe(source, sink);
    }
  }
  
  public void encode(LocalType source, RemoteType sink) throws CodecException
  {
    for (FieldTranscriber<LocalType, RemoteType> encoder : 
            fieldEncoders.values())
    {
      encoder.transcribe(source, sink);
    }
  }
  
  public LocalType decode(RemoteType source) throws CodecException
  {
    LocalType sink = instantiateLocal();
    decode(source, sink);
    return sink;
  }
  
  public RemoteType encode(LocalType source) throws CodecException
  {
    RemoteType sink = instantiateRemote();
    encode(source, sink);
    return sink;
  }
  
  public void setFieldEncoder(String key, 
                              FieldTranscriber<LocalType, RemoteType> transcriber)
  {
    fieldEncoders.put(key, transcriber);
  }
  
  public FieldTranscriber<LocalType, RemoteType> getFieldEncoder(String key)
  {
    return fieldEncoders.get(key);
  }
  
  public boolean hasFieldEncoder(String key)
  {
    return fieldEncoders.containsKey(key);
  }
  
  public void setFieldDecoder(String key, 
                              FieldTranscriber<RemoteType, LocalType> transcriber)
  {
    fieldDecoders.put(key, transcriber);
  }
  
  public FieldTranscriber<RemoteType, LocalType> getFieldDecoder(String key)
  {
    return fieldDecoders.get(key);
  }
  
  public boolean hasFieldDecoder(String key)
  {
    return fieldDecoders.containsKey(key);
  }
}
