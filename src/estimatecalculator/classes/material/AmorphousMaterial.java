/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estimatecalculator.classes.material;

// Для сыпучих и жидких материалов, как однородных так и составных смесей
public class AmorphousMaterial  {

    private String name;
    private double density; // плотность  кг/м³
    private UnitEnum unit; // Единица измерения
    private int defaultPackageID; // id упаковки по умолчанию. М.б. несколько доступных упаковок
    private int defaultDeliveryID; // id доставки по умолчанию
    private int defaultManufacturerID; // id производителя по умолчанию
    private int defaultVendorID; // id поставщика по умолчанию
    private double proportion; // Пропорция по массе. если это материал в чистром виде, то 100, если это состав раствора, то от 1 до 99. Не забывать приводить к единой единице
    private double price; // Цена за единицу измерения



}
