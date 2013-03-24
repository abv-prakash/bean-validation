package com.abhinav.examples.hibernate;

import java.util.Iterator;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * Hello world!
 * 
 */
public class Car
{

    private final String  manufacturer;
    @NotNull
    @Size(min = 2, max = 14)
    private final String  licensePlate;
    @Min(2)
    private final int     seatCount;

    @AssertTrue(message = "Car should be new")
    private final boolean isNew;

    public Car(final String manufacturer, final String licencePlate, final int seatCount)
    {
        this.manufacturer = manufacturer;
        this.licensePlate = licencePlate;
        this.seatCount = seatCount;
        this.isNew = true;
    }

    @NotEmpty
    public String getManufacturer()
    {
        return this.manufacturer;
    }

    public static void main(final String[] args)
    {
        System.out.println("Hello World!");
        String manufacture = null;
        Car car = new Car(manufacture, "123", 3);

        System.out.println(car.getManufacturer());

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Car>> constraintViolations = validator.validate(car);

        System.out.println(constraintViolations.size());

        if (constraintViolations.size() > 0)
        {
            Iterator<ConstraintViolation<Car>> iterator = constraintViolations.iterator();
            while (iterator.hasNext())
            {
                ConstraintViolation<Car> violatedConstraint = iterator.next();

                System.out.println(violatedConstraint.getMessage() + "- For param :"
                        + violatedConstraint.getPropertyPath() + "- Invalid value given :"
                        + violatedConstraint.getInvalidValue());
            }

        }

    }
}
