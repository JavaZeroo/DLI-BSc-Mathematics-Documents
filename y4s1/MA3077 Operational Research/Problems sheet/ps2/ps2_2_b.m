% Problem 2.b
% Expected Output
% Selected edges for the minimal spanning tree:
% Edge: O-B
% Edge: O-C
% Edge: A-B

clear; close all;

% O A B C
% 1 2 3 4
% create a matrix with edges: initial node, final node, and weight
edges = [
    1 2 5;
    1 3 2;
    1 4 3;
    2 3 3;
    3 4 8;
];

node_names = {'O', 'A', 'B', 'C'};
nodes = unique(edges(:, 1:2));
num_nodes = length(nodes);
num_edges = size(edges, 1);

f = edges(:, 3);

Aeq = ones(1, num_edges);
beq = num_nodes - 1;

A = [];
b = [];

% Inequality Constraint
for k = 2:num_nodes-1
    combn = nchoosek(nodes, k);
    for i = 1:size(combn, 1)
        constraint = zeros(1, num_edges);
        for j = 1:num_edges
            if all(ismember(edges(j, 1:2), combn(i, :)))
                constraint(j) = 1;
            end
        end
        A = [A; constraint];
        b = [b; k - 1];
    end
end

% Variable Range
lb = zeros(num_edges, 1);
ub = ones(num_edges, 1);

intcon = 1:num_edges;
[x, fval] = intlinprog(f, intcon, A, b, Aeq, beq, lb, ub);

disp('Selected edges for the minimal spanning tree:');
for i = 1:num_edges
    if x(i) == 1
        disp(['Edge: ' node_names{edges(i, 1)} '-' node_names{edges(i, 2)}]);
    end
end
