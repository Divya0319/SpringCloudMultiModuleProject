package com.fastturtle.fortuneconsumer.repos;

import com.fastturtle.fortuneconsumer.models.Fortune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FortuneRepo extends JpaRepository<Fortune, Integer> {
}
