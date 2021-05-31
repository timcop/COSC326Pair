using Plots
average_wait_fifs = []
average_turnover_fifs = []
average_wait_ud = []
average_turnover_ud = []
average_wait_sjf = []
average_turnover_sjf = []
for i = 1:250
    file = "data250/averages"
    file = string(file, i)
    file = string(file, ".txt")
    f = open(file)
    lines = readlines(f);
    avg = (split(lines[1]))
    avg = [parse(Float64, x) for x in avg]
    for line in lines
        line = split(line)
        line = [parse(Float64, x) for x in line]
        avg = avg + line
    end
    avg = avg / length(lines)
    append!(average_wait_fifs, avg[1])
    append!(average_turnover_fifs, avg[2])
    append!(average_wait_ud, avg[3])
    append!(average_turnover_ud, avg[4])
    append!(average_wait_sjf, avg[5])
    append!(average_turnover_sjf, avg[6])
end

N = [1:250]
# plot(N, average_wait_fifs/60, label = "FIFS")
# plot!(N, average_turnover_fifs)
plot(N, average_wait_ud/60, label = "UD Wait", legend=:topleft)
plot!(N, average_turnover_ud/60, label = "UD Turnover")
plot!(N, average_wait_sjf/60, label = "SJF Wait")
plot2 = plot!(N, average_turnover_sjf/60, label = "SJF Turnover")
title!("Average Turnover and Wait times: Hour Window")
xlabel!("Customers")
ylabel!("Minutes")


savefig(plot2, "UD_SJF_250.png")