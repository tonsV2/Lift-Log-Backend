package dk.fitfit.liftlog.service;

import dk.fitfit.liftlog.domain.Exercise;
import dk.fitfit.liftlog.domain.WorkoutSet;
import dk.fitfit.liftlog.domain.User;
import dk.fitfit.liftlog.repository.WorkoutSetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WorkoutSetService {
	private final WorkoutSetRepository workoutSetRepository;

	@Autowired
	public WorkoutSetService(WorkoutSetRepository workoutSetRepository) {
		this.workoutSetRepository = workoutSetRepository;
	}

	public Iterable<WorkoutSet> findAll() {
		return workoutSetRepository.findAll();
	}

	public WorkoutSet log(User user, Exercise exercise, WorkoutSet set) {
		set.setUser(user);
		set.setExercise(exercise);
		return workoutSetRepository.save(set);
	}

	public WorkoutSet log(User user, Exercise exercise, int repetition, double weight) {
		WorkoutSet set = new WorkoutSet();
		set.setRepetition(repetition);
		set.setWight(weight);
		return log(user, exercise, set);
	}

	public WorkoutSet log(User user, Exercise exercise, int repetition, double weight, LocalDateTime localDateTime) {
		WorkoutSet set = new WorkoutSet();
		set.setRepetition(repetition);
		set.setWight(weight);
		set.setTimestamp(localDateTime);
		return log(user, exercise, set);
	}

	public WorkoutSet save(WorkoutSet set) {
		return workoutSetRepository.save(set);
	}
}