package com.sailorswift.conferencedemo.repositories;

import com.sailorswift.conferencedemo.models.Session;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessionRepository extends JpaRepository<Session, Long> {
}
