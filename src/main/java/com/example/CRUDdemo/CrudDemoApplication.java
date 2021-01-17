package com.example.CRUDdemo;

import com.example.CRUDdemo.Models.Task;
import com.example.CRUDdemo.Repositories.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudDemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner runApplication(TaskRepository taskRepository) {
		return (args -> {
			// Create methods
			Task articleTask = new Task("Finish article", "Finish the article and send for review", true, 0);
			taskRepository.save(articleTask);

			Task speechTask = new Task("Meeting speech", "Prepare the speech for the meeting", false, 2);
			taskRepository.save(speechTask);

			Task drTask = new Task("Call Dr Robbins", "Cancel the visit", true, 1);
			taskRepository.save(drTask);

			Task bookingTask = new Task("Book a hotel", "Book a hotel for vacation", true, 4);
			Task savedTask = taskRepository.save(bookingTask);

			// Read methods
			System.out.println("Task list:");
			for (Task task : taskRepository.findAll()) {
				System.out.println(task.toString());
			}

			System.out.println("List of enabled tasks:");
			for (Task task : taskRepository.findAllByEnabled(true)) {
				System.out.println(task.toString());
			}

			// Update methods
			performUpdateOperations(taskRepository);

			// Delete methods
			// deleteAll();
		});
	}

	private void performUpdateOperations(TaskRepository taskRepository) {
		System.out.println("Updating tasks's title");
		Task task = taskRepository.findByTitle("Meeting speech");

		if (task != null) {
			System.out.println(task.toString());
			task.setEnabled(true);
			taskRepository.save(task);
			System.out.println(taskRepository.findByTitle("Meeting speech"));
		}
	}

}
