/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.org.ncallister.goodbudget.tools.codec;

/**
 *
 * @author ncallister
 */
public interface FieldTranscriber<SourceType, SinkType>
{
  void transcribe(SourceType source, SinkType sink) throws CodecException;
}
