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
public class CodecException extends Exception
{
  public CodecException(String message)
  {
    super(message);
  }
  
  public CodecException(Throwable cause)
  {
    super(cause);
  }
  
  public CodecException(String message, Throwable cause)
  {
    super(message, cause);
  }
}
