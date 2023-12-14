import 'package:flutter/material.dart';
import 'package:get/get.dart';
import 'package:mobileapp/app/modules/home/controller/catalogue_controller.dart';
import 'package:mobileapp/core/theme/colors.dart';
import 'package:ristek_material_component/ristek_material_component.dart';

class FilterSearchProduct extends StatelessWidget {
  const FilterSearchProduct({super.key});

  @override
  Widget build(BuildContext context) {
    var catalogueController = Get.find<CatalogueController>();
    return Column(
      children: [
        Row(
          mainAxisAlignment: MainAxisAlignment.spaceBetween,
          children: [
            Text(
              'Catalogue',
              style: Theme.of(context).textTheme.headlineMedium,
            ),
            // filter
            GestureDetector(
                onTap: () {
                  showDialog(
                    context: context,
                    builder: (context) => dialogFilter(context),
                  );
                },
                child: Row(
                  children: [
                    const Icon(Icons.filter_alt, color: MyColors.primary),
                    const SizedBox(width: 4),
                    Text('Filter',
                        style:
                            Theme.of(context).textTheme.headlineSmall?.copyWith(
                                  color: MyColors.primary,
                                )),
                  ],
                )),
          ],
        ),
        const SizedBox(height: 16),
        // search
        SearchField(
          controller: catalogueController.searchController,
          focusNode: catalogueController.searchFocusNode,
          hintText: 'Search Product',
        )
      ],
    );
  }
}

// make dialog filter
Widget dialogFilter(BuildContext context) {
  return Dialog(
    child: Container(
      padding: const EdgeInsets.all(16),
      child: Column(
        mainAxisSize: MainAxisSize.min,
        children: [Text('under development')],
      ),
    ),
  );
}
