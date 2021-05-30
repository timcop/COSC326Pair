javac -d . tims_soln/*.java tims_soln/Strategies/*.java

for /l %%x in (1, 1, 250) do (
    for /l %%y in (1, 1, 50) do (
        java elevator.Test %%x
    )
)
