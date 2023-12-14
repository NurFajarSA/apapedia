import 'dart:convert';

import 'package:mobileapp/app/data/models/product.dart';
import 'package:mobileapp/app/data/providers/api.dart';
import 'package:http/http.dart' as http;

class CatalogueService {
  Future<List<Product>> getProducts() async {
    var url = Uri.parse("${Api.baseUrlCatalogue}/catalogue/view-all");
    var response = await http.get(url);
    if (response.statusCode == 200) {
      return Product.fromJsonList(response.body);
    } else {
      throw Exception('Failed to load products');
    }
  }

  Future<Product> getProductDetail(String id) async {
    var url = Uri.parse("${Api.baseUrlCatalogue}/catalogue/$id");
    var response = await http.get(url);
    if (response.statusCode == 200) {
      return Product.fromJson(jsonDecode(response.body));
    } else {
      throw Exception('Failed to load product detail');
    }
  }
}
