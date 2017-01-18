package org.batfish.datamodel.routing_policy.statement;

import java.util.List;

import org.batfish.datamodel.AsPath;
import org.batfish.datamodel.AsSet;
import org.batfish.datamodel.BgpRoute;
import org.batfish.datamodel.routing_policy.Environment;
import org.batfish.datamodel.routing_policy.Result;
import org.batfish.datamodel.routing_policy.expr.AsPathListExpr;

import com.fasterxml.jackson.annotation.JsonCreator;

public class PrependAsPath extends Statement {

   /**
    *
    */
   private static final long serialVersionUID = 1L;

   private AsPathListExpr _expr;

   @JsonCreator
   private PrependAsPath() {
   }

   public PrependAsPath(AsPathListExpr expr) {
      _expr = expr;
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) {
         return true;
      }
      if (obj == null) {
         return false;
      }
      if (getClass() != obj.getClass()) {
         return false;
      }
      PrependAsPath other = (PrependAsPath) obj;
      if (_expr == null) {
         if (other._expr != null) {
            return false;
         }
      }
      else if (!_expr.equals(other._expr)) {
         return false;
      }
      return true;
   }

   @Override
   public Result execute(Environment environment) {
      List<Integer> toPrepend = _expr.evaluate(environment);
      BgpRoute.Builder bgpRouteBuilder = (BgpRoute.Builder) environment
            .getOutputRoute();
      AsPath asPath = bgpRouteBuilder.getAsPath();
      for (Integer as : toPrepend) {
         AsSet asSet = new AsSet();
         asSet.add(as);
         asPath.add(0, asSet);
      }
      Result result = new Result();
      return result;
   }

   public AsPathListExpr getExpr() {
      return _expr;
   }

   @Override
   public int hashCode() {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((_expr == null) ? 0 : _expr.hashCode());
      return result;
   }

   public void setExpr(AsPathListExpr expr) {
      _expr = expr;
   }

}
