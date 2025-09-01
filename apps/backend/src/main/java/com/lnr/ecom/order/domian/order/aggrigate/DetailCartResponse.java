package com.lnr.ecom.order.domian.order.aggrigate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.jilt.Builder;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DetailCartResponse {


  List<ProductCart> products;

}
