import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/data/models/product.dart';
import 'package:mobileapp/app/modules/home/catalogue/widget/card_product.dart';
import 'package:mobileapp/app/modules/home/controller/catalogue_controller.dart';

class ListProduct extends StatelessWidget {
  const ListProduct({super.key});

  @override
  Widget build(BuildContext context) {
    final catalogueController = Get.find<CatalogueController>();
    // fetch data from api
    return Container(
      width: MediaQuery.of(context).size.width,
      child: FutureBuilder(
        future: catalogueController.fetchProducts(),
        builder: (context, snapshot) {
          if (snapshot.hasData) {
            return GridView.builder(
              shrinkWrap: true,
              physics: const NeverScrollableScrollPhysics(),
              itemCount: (snapshot.data as List<Product>).length,
              gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 2,
                childAspectRatio: 0.7,
              ),
              itemBuilder: (context, index) {
                return CardProduct(
                  product: (snapshot.data as List<Product>)[index],
                );
              },
            );
          } else if (snapshot.hasError) {
            return const Center(
              child: Text("Error"),
            );
          } else {
            return const Center(
              child: CircularProgressIndicator(),
            );
          }
        },
      ),
    );
  }
}
