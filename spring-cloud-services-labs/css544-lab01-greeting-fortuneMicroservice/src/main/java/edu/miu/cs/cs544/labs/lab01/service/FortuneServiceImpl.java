package edu.miu.cs.cs544.labs.lab01.service;

import java.util.Random;

import org.springframework.stereotype.Service;

import edu.miu.cs.cs544.labs.lab01.domain.Fortune;

@Service
public class FortuneServiceImpl implements FortuneService {

  private static final Fortune[] Fortunes = {
      new Fortune(1, "You learn from your mistakes... You will learn a lot today."),
      new Fortune(2, "You can always find happiness at work on Friday"),
      new Fortune(3, "You will be hungry again in one hour."),
      new Fortune(4, "Today will be an awesome day!")
  };

  private Random random = new Random();

  public Fortune getFortune() {
    return Fortunes[randomIndexIntoFortunes()];
  }

  private int randomIndexIntoFortunes() {
    return random.nextInt(Fortunes.length);
  }

}
