import 'package:flutter/material.dart';
import 'package:mobileapp/app/modules/home/catalogue/widget/card_product.dart';

class ListProduct extends StatelessWidget {
  const ListProduct({super.key});

  @override
  Widget build(BuildContext context) {
    // fetch data from api
    return Container(
      width: MediaQuery.of(context).size.width,
      child: Column(
        children: [
          GridView.builder(
            shrinkWrap: true,
            physics: const NeverScrollableScrollPhysics(),
            itemCount: 10,
            gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(
              crossAxisCount: 2,
              childAspectRatio: 0.67,
              crossAxisSpacing: 16,
              mainAxisSpacing: 16,
            ),
            itemBuilder: (context, index) {
              return const CardProduct(
                  productName:
                      'Xiaomi Redmi Note 10 Pro 8GB 128GB - Glacier Blue',
                  productPrice: '3.999.000',
                  productImage:
                      'https://i01.appmifile.com/webfile/globalimg/id/cms/F5754513-7B21-DB5E-8CE9-C5101EED530C!800x800!85.jpg');
            },
          ),
        ],
      ),
    );
  }
}
