package racingcar;

import java.util.*;

import camp.nextstep.edu.missionutils.*;

public class Application {
    public static void main(String[] args) {
        RacingGame game = new RacingGame();
        game.start();
    }
}

class Car {
    private final String name;
    private int position = 0;

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void move() {
        int randomNumber = Randoms.pickNumberInRange(0, 9);
        if (randomNumber >= 4) {
            position++;
        }
    }
}

class RacingGame {
    public void start() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String carName = Console.readLine();

        List<Car> cars = createCarsFromInput(carName);

        System.out.println("시도할 횟수는 몇 회인가요?");
        int attempt = Integer.parseInt(Console.readLine());

        System.out.println("\n실행 결과");
        for (int i = 0; i < attempt; i++) {
            for (int j = 0; j < cars.size(); j++) {
                Car car = cars.get(j);
                car.move();
            }
            printCurrentStatus(cars);
            System.out.println();
        }

        List<String> winners = findWinners(cars);
        System.out.println("최종 우승자 : " + String.join(", ", winners));
    }

    private List<Car> createCarsFromInput(String carNameInput) {
        String[] names = carNameInput.split(",");
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            if (names[i].length() > 5) {
                throw new IllegalArgumentException("이름은 5자 이하만 가능합니다.");
            } else if (names[i].isEmpty()) {
                throw new IllegalArgumentException("이름을 입력하지 않았습니다. 다시 입력해주세요.");
            } else {
                Car car = new Car(names[i]);
                cars.add(car);
            }
        }
        return cars;
    }

    private void printCurrentStatus(List<Car> cars) {
        for (Car car : cars) {
            System.out.print(car.getName() + " : ");

            for (int i = 0; i < car.getPosition(); i++) {
                System.out.print("-");
            }

            System.out.println();
        }
    }

    private List<String> findWinners(List<Car> cars) {

        int maxPosition = 0;

        for (Car car : cars) {
            if (car.getPosition() > maxPosition) {
                maxPosition = car.getPosition();
            }
        }

        List<String> winners = new ArrayList<>();

        for (Car car : cars) {
            if (car.getPosition() == maxPosition) {
                winners.add(car.getName());
            }
        }

        return winners;
    }
}