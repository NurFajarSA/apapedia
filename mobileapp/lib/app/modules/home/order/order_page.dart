import 'package:flutter/material.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class OrderPage extends StatelessWidget {
  const OrderPage({super.key});

  @override
  Widget build(BuildContext context) {
    return SingleChildScrollView(
        child: Container(
      width: MediaQuery.of(context).size.width,
      margin: const EdgeInsets.all(20.0),
      child: Column(crossAxisAlignment: CrossAxisAlignment.start, children: [
        Text(
          'Order History',
          style: Theme.of(context).textTheme.headlineMedium,
        ),
        const SizedBox(height: 16),
        ListView.separated(
          shrinkWrap: true,
          physics: const NeverScrollableScrollPhysics(),
          itemCount: 5,
          separatorBuilder: (context, index) => const SizedBox(height: 16),
          itemBuilder: (context, index) => Container(
            padding: EdgeInsets.all(16),
            decoration: BoxDecoration(
              color: Colors.white,
              borderRadius: BorderRadius.circular(8),
              boxShadow: BoxShadowDecorator().defaultShadow(context),
            ),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              children: [
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(
                      'Toko Apap',
                      style: Theme.of(context).textTheme.labelMedium?.copyWith(
                            color: Colors.grey,
                          ),
                    ),
                    Text(
                      '12/12/2021',
                      style: Theme.of(context).textTheme.labelMedium?.copyWith(
                            color: Colors.grey,
                          ),
                    ),
                  ],
                ),
                const SizedBox(height: 8),
                Row(
                  children: [
                    Expanded(
                      child: Text(
                        'Xiaomi Redmi Note 10 Pro 8GB 128GB - Glacier Blue',
                        style: Theme.of(context).textTheme.titleSmall?.copyWith(
                              color: Colors.black,
                            ),
                      ),
                    ),
                    Text(
                      'Rp 100.000',
                      style: Theme.of(context).textTheme.labelMedium?.copyWith(
                            color: Colors.black,
                          ),
                    ),
                  ],
                ),
                const SizedBox(height: 8),
                Row(
                  children: [
                    Expanded(
                      child: Text(
                        'Xiaomi Redmi Note 10 Pro 8GB 128GB - Glacier Blue',
                        style: Theme.of(context).textTheme.titleSmall?.copyWith(
                              color: Colors.black,
                            ),
                      ),
                    ),
                    Text(
                      'Rp 100.000',
                      style: Theme.of(context).textTheme.labelMedium?.copyWith(
                            color: Colors.black,
                          ),
                    ),
                  ],
                ),
                const SizedBox(height: 8),
                Row(
                  children: [
                    Expanded(
                      child: Text(
                        'Xiaomi Redmi Note 10 Pro 8GB 128GB - Glacier Blue',
                        style: Theme.of(context).textTheme.titleSmall?.copyWith(
                              color: Colors.black,
                            ),
                      ),
                    ),
                    Text(
                      'Rp 100.000',
                      style: Theme.of(context).textTheme.labelMedium?.copyWith(
                            color: Colors.black,
                          ),
                    ),
                  ],
                ),
                const SizedBox(height: 8),
                Row(
                  children: [
                    Expanded(
                      child: Text(
                        'Xiaomi Redmi Note 10 Pro 8GB 128GB - Glacier Blue',
                        style: Theme.of(context).textTheme.titleSmall?.copyWith(
                              color: Colors.black,
                            ),
                      ),
                    ),
                    Text(
                      'Rp 100.000',
                      style: Theme.of(context).textTheme.labelMedium?.copyWith(
                            color: Colors.black,
                          ),
                    ),
                  ],
                ),
                // total harga
                const SizedBox(height: 8),
                Row(
                  mainAxisAlignment: MainAxisAlignment.spaceBetween,
                  children: [
                    Text(
                      'Total',
                      style: Theme.of(context).textTheme.labelMedium?.copyWith(
                            color: Colors.grey,
                          ),
                    ),
                    Text(
                      'Rp 100.000',
                      style: Theme.of(context).textTheme.labelMedium?.copyWith(
                          color: MyColors.primary, fontWeight: FontWeight.bold),
                    ),
                  ],
                ),
              ],
            ),
          ),
        )
      ]),
    ));
  }
}
