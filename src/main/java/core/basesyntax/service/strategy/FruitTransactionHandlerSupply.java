package core.basesyntax.service.strategy;

import core.basesyntax.dao.FruitDao;
import core.basesyntax.model.FruitItem;
import core.basesyntax.model.FruitTransaction;
import core.basesyntax.service.FruitItemService;

public class FruitTransactionHandlerSupply implements FruitTransactionHandler {

    @Override
    public void execute(FruitTransaction fruitTransaction,
                        FruitDao fruitDao,
                        FruitItemService fruitItemService) {
        String fruitName = fruitTransaction.getFruitName();
        int fruitQuantity = fruitTransaction.getQuantity();
        int fruitOldQuantity;
        if (fruitDao.containsName(fruitName)) {
            fruitOldQuantity = fruitDao.getByName(fruitName).getQuantity();
        } else {
            fruitOldQuantity = 0;
        }
        int fruitNewQuantity = fruitOldQuantity + fruitQuantity;
        FruitItem newFruitItem = fruitItemService.create(fruitName, fruitNewQuantity);
        fruitDao.put(newFruitItem);
    }
}
