package org.batfish.datamodel.routing_policy.statement;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import org.batfish.datamodel.routing_policy.Environment;
import org.batfish.datamodel.routing_policy.Result;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
@JsonSubTypes({ @JsonSubTypes.Type(value = AddCommunity.class),
      @JsonSubTypes.Type(value = BufferedStatement.class),
      @JsonSubTypes.Type(value = CallStatement.class),
      @JsonSubTypes.Type(value = Comment.class),
      @JsonSubTypes.Type(value = DeleteCommunity.class),
      @JsonSubTypes.Type(value = If.class),
      @JsonSubTypes.Type(value = PrependAsPath.class),
      @JsonSubTypes.Type(value = RetainCommunity.class),
      @JsonSubTypes.Type(value = SetCommunity.class),
      @JsonSubTypes.Type(value = SetIsisLevel.class),
      @JsonSubTypes.Type(value = SetIsisMetricType.class),
      @JsonSubTypes.Type(value = SetLocalPreference.class),
      @JsonSubTypes.Type(value = SetMetric.class),
      @JsonSubTypes.Type(value = SetNextHop.class),
      @JsonSubTypes.Type(value = SetOrigin.class),
      @JsonSubTypes.Type(value = SetOspfMetricType.class),
      @JsonSubTypes.Type(value = SetTag.class),
      @JsonSubTypes.Type(value = SetVarMetricType.class),
      @JsonSubTypes.Type(value = SetWeight.class) })
public abstract class Statement implements Serializable {

   private static final String COMMENT_VAR = "comment";

   /**
    *
    */
   private static final long serialVersionUID = 1L;

   private String _comment;

   @Override
   public abstract boolean equals(Object obj);

   public abstract Result execute(Environment environment);

   @JsonProperty(COMMENT_VAR)
   public final String getComment() {
      return _comment;
   }

   @Override
   public abstract int hashCode();

   @JsonProperty(COMMENT_VAR)
   public final void setComment(String comment) {
      _comment = comment;
   }

   public List<Statement> simplify() {
      return Collections.singletonList(this);
   }

   @Override
   public String toString() {
      if (_comment != null) {
         return getClass().getSimpleName() + "<" + _comment + ">";
      }
      else {
         return super.toString();
      }
   }

}
