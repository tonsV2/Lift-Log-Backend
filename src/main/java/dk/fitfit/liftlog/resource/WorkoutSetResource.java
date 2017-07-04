package dk.fitfit.liftlog.resource;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import dk.fitfit.liftlog.domain.WorkoutSet;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public class WorkoutSetResource extends ResourceObject {
	private int repetition;
	private double weight;
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	private LocalDateTime timestamp;
	private ExerciseResource exercise;

	public WorkoutSetResource() {
	}

	private WorkoutSetResource(WorkoutSet workoutSet) {
		this.repetition = workoutSet.getRepetition();
		this.weight = workoutSet.getWeight();
		this.timestamp = workoutSet.getTimestamp();
		this.exercise = ExerciseResource.from(workoutSet.getExercise());
	}

	public static Iterable<WorkoutSetResource> from(Set<WorkoutSet> sets) {
		return sets.stream()
				.map(WorkoutSetResource::from)
				.collect(Collectors.toSet());
	}

	public static WorkoutSetResource from(WorkoutSet workoutSet) {
		return new WorkoutSetResource(workoutSet);
	}

	public int getRepetition() {
		return repetition;
	}

	public void setRepetition(int repetition) {
		this.repetition = repetition;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public ExerciseResource getExercise() {
		return exercise;
	}

	public void setExercise(ExerciseResource exercise) {
		this.exercise = exercise;
	}
}
