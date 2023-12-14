import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/data/models/product.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:mobileapp/routes/routes.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class CardProduct extends StatelessWidget {
  const CardProduct({super.key, required this.product});
  // parameternya nama produk, harga, dan gambar
  final Product product;

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () {
        Get.toNamed(Routes.PRODUCT_DETAIL);
      },
      child: Container(
        decoration: BoxDecoration(
          color: MyColors.white,
          boxShadow: BoxShadowDecorator().defaultShadow(context),
          borderRadius: BorderRadius.circular(8),
        ),
        child: Column(
          children: [
            Container(
              height: 150,
              decoration: const BoxDecoration(
                image: DecorationImage(
                  image: NetworkImage(
                      "https://scontent.fcgk19-1.fna.fbcdn.net/v/t39.30808-6/309928275_159922883346554_1498437531423887807_n.jpg?_nc_cat=105&ccb=1-7&_nc_sid=efb6e6&_nc_ohc=EnXpnetzPSMAX8fZrED&_nc_pt=1&_nc_ht=scontent.fcgk19-1.fna&oh=00_AfCEStzHdGCFYUw4G0bWZUPuOeBepmRnRYgsqQscIYwaEQ&oe=657F93CB"),
                  fit: BoxFit.cover,
                ),
              ),
            ),
            Container(
              padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
              child: Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children: [
                  Text(
                    product.productName,
                    style: Theme.of(context).textTheme.labelMedium,
                  ),
                  const SizedBox(height: 4),
                  Text(
                    'Rp ${product.price}',
                    style: Theme.of(context).textTheme.headlineSmall,
                  ),
                  const SizedBox(height: 4),
                  // dua button buy dan add to cart
                  Row(
                    mainAxisAlignment: MainAxisAlignment.spaceBetween,
                    children: [
                      // button buy
                      SecondaryButton(
                        width: MediaQuery.of(context).size.width * 0.17,
                        onPressed: () {},
                        child: const Center(
                          child: Text(
                            'Buy',
                            style: TextStyle(color: MyColors.primary),
                          ),
                        ),
                      ),
                      // button add to cart
                      PrimaryButton(
                        width: MediaQuery.of(context).size.width * 0.17,
                        onPressed: () {},
                        child: const Row(
                          children: [
                            Icon(Icons.add_shopping_cart, color: Colors.white),
                            SizedBox(width: 4),
                            Text(
                              'Cart',
                              style: TextStyle(color: Colors.white),
                            ),
                          ],
                        ),
                      )
                    ],
                  ),
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
