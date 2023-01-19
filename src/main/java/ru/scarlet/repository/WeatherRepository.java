package ru.scarlet.repository;

public interface WeatherRepository extends org.springframework.data.jpa.repository.JpaRepository<ru.scarlet.entity.WeatherToTg,java.lang.Integer> {
    boolean existsByCity(java.lang.String city);

    @org.springframework.data.jpa.repository.Query("select w from WeatherToTg w where w.city= :city")
    ru.scarlet.entity.WeatherToTg findByCity(java.lang.String city);


}
