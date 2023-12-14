import 'package:flutter/material.dart';
import 'package:mobileapp/app/modules/home/catalogue/widget/filter_search_product.dart';
import 'package:mobileapp/app/modules/home/catalogue/widget/list_product.dart';

class CataloguePage extends StatelessWidget {
  const CataloguePage({super.key});

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
      child: Container(
        width: MediaQuery.of(context).size.width,
        margin: const EdgeInsets.all(16),
        child: const Column(
          children: [
            FilterSearchProduct(),
            SizedBox(height: 16),
            ListProduct()
          ],
        ),
      ),
    );
  }
}
