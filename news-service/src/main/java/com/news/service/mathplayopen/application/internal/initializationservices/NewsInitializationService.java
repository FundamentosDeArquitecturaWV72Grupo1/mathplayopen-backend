package com.news.service.mathplayopen.application.internal.initializationservices;

import com.news.service.mathplayopen.application.internal.commandservices.NewsCommandServiceImpl;
import com.news.service.mathplayopen.application.internal.queryservices.NewsQueryServiceImpl;
import com.news.service.mathplayopen.domain.model.aggregates.News;
import com.news.service.mathplayopen.domain.model.commands.CreateNewsCommand;
import com.news.service.mathplayopen.domain.model.queries.GetNewsByTitleQuery;
import com.news.service.mathplayopen.domain.model.valueobjects.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NewsInitializationService {
    private final NewsCommandServiceImpl newsCommandService;
    private final NewsQueryServiceImpl newsQueryService;

    @Autowired
    public NewsInitializationService(NewsCommandServiceImpl newsCommandService, NewsQueryServiceImpl newsQueryService) {
        this.newsCommandService = newsCommandService;
        this.newsQueryService = newsQueryService;
    }

    @PostConstruct
    public void init() {
        List<CreateNewsCommand> newsToCreate = List.of(
                new CreateNewsCommand(
                        new Author("Carlos Ramírez"),
                        new Title("¡Inicia el Concurso Anual de Ciencias en el Colegio San Juan!"),
                        new Description("Este mes, el Colegio lanza su esperado Concurso Anual de Ciencias. Habrá premios para los tres mejores proyectos y la oportunidad de representar a la escuela en la competencia regional."),
                        "https://www.innovaschools.edu.pe/",
                        new Source("Boletín Informativo del Colegio San Juan"),
                        new Image("https://static.vecteezy.com/system/resources/previews/000/202/099/original/science-fair-concept-vector.jpg"),
                        new Category("Eventos Escolares")
                ),
                new CreateNewsCommand(
                        new Author("Ana Torres"),
                        new Title("Resultados del Primer Examen Trimestral: ¡A Celebrar los Logros!"),
                        new Description("Los estudiantes del Colegio han recibido sus calificaciones del primer examen trimestral. La mayoría ha mostrado un gran esfuerzo. La dirección del colegio felicita a todos por su dedicación. ¡Sigue adelante, estudiantes!"),
                        "https://www.innovaschools.edu.pe/",
                        new Source("Boletín Informativo del Colegio San Juan"),
                        new Image("https://image.slidesharecdn.com/repaso-20examen-20unidad-201-130915211000-phpapp01/95/repaso-examen-unidad-1-42-638.jpg?cb=1379279503"),
                        new Category("Académico")
                ),
                new CreateNewsCommand(
                        new Author("Luis Hernández"),
                        new Title("Nueva Clase de Programación Abierta para Todos los Estudiantes"),
                        new Description("El Colegio ha lanzado una nueva clase de programación que comenzará el próximo mes. Los participantes aprenderán sobre fundamentos de programación con Scratch. Las inscripciones están abiertas hasta el 20 de noviembre, así que no pierdas la oportunidad."),
                        "https://www.innovaschools.edu.pe/",
                        new Source("Boletín Informativo del Colegio San Juan"),
                        new Image("https://soundinfusion.io/wp-content/uploads/2020/04/SI_BlogPost_4-1024x683.jpg"),
                        new Category("Educación")
                ),
                new CreateNewsCommand(
                        new Author("Sofía Martínez"),
                        new Title("Excursión Educativa a la Reserva Natural: ¡Una Experiencia Única!"),
                        new Description("El próximo 10 de diciembre, los estudiantes tendrán la oportunidad de participar en una excursión educativa a la Reserva Natural El Encanto. Aprenderán sobre la biodiversidad local y la importancia de conservar nuestro medio ambiente."),
                        "",
                        new Source("Boletín Informativo del Colegio San Juan"),
                        new Image("https://agn.gt/wp-content/uploads/2022/07/FY64eHeXoAARY4g.jpg"),
                        new Category("Actividades Extracurriculares")
                ),
                new CreateNewsCommand(
                        new Author("Javier Gómez"),
                        new Title("Feria Cultural: Celebrando la Diversidad en el Colegio San Juan"),
                        new Description("El 15 de diciembre se llevará a cabo la Feria Cultural en el patio central. Este evento es una celebración de las diversas culturas que componen nuestra comunidad escolar. Habrá presentaciones artísticas, degustaciones culinarias, entre otros."),
                        "",
                        new Source("Boletín Informativo del Colegio San Juan"),
                        new Image("https://i.ytimg.com/vi/OimtJA9u7_o/maxresdefault.jpg"),
                        new Category("Cultura")
                )
        );

        for (CreateNewsCommand command : newsToCreate) {
            Optional<News> existingNews = newsQueryService.handle(new GetNewsByTitleQuery(command.title()));
            if (existingNews.isEmpty()) {
                try {
                    newsCommandService.handle(command);
                } catch (RuntimeException e) {
                    System.out.println("Error creating news: " + e.getMessage());
                }
            } else {
                System.out.println("The news already exists: " + command.title());
            }
        }
    }
}
