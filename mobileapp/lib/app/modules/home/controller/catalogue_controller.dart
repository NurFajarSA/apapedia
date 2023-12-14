import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/data/models/product.dart';
import 'package:mobileapp/app/data/services/catalogue_service.dart';

class CatalogueController extends GetxController {
  var searchController = TextEditingController();
  var searchFocusNode = FocusNode();

  fetchProducts() async {
    List<Product> products = await Get.find<CatalogueService>().getProducts();
    return products;
  }
}
