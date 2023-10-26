package racingcar.domain;

import java.util.Collections;
import java.util.List;

import static racingcar.exception.ExceptionMessage.CarException.DUPLICATE_CAR;
import static racingcar.exception.ExceptionMessage.CarException.NO_PARTICIPANTS;

public class Cars {
    private final List<Car> cars;

    private Cars(final List<Car> cars) {
        this.cars = cars;
    }

    public static Cars from(final List<Car> cars) {
        validateParticipantExists(cars);
        validateDuplicateCarExists(cars);
        return new Cars(cars);
    }

    private static void validateParticipantExists(final List<Car> cars) {
        if (cars.isEmpty()) {
            throw new IllegalArgumentException(NO_PARTICIPANTS.message);
        }
    }

    private static void validateDuplicateCarExists(final List<Car> cars) {
        if (hasDuplicateCar(cars)) {
            throw new IllegalArgumentException(DUPLICATE_CAR.message);
        }
    }

    private static boolean hasDuplicateCar(final List<Car> cars) {
        return cars.stream()
                .distinct()
                .count() != cars.size();
    }

    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }
}
